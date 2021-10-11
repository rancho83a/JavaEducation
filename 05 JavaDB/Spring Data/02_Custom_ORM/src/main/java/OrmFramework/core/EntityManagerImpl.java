package OrmFramework.core;

import OrmFramework.annotation.Column;
import OrmFramework.annotation.Entity;
import OrmFramework.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EntityManagerImpl implements EntityManager {
    private final Connection connection;

    public EntityManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> T findEntityById(int id, Class<T> type) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String tableName = type.getAnnotation(Entity.class).tableName();
        String idColumnName = Arrays.stream(type.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst().orElseThrow().getName();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?");
        stmt.setInt(1, id);

        T entity = (T) type.getConstructors()[0].newInstance();

        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnInfo = field.getAnnotation(Column.class);
                String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                if (field.getType().equals(String.class)) {
                    String s = rs.getString(columnInfo.name());
                    type.getMethod(setterName, String.class).invoke(entity, s);
                } else if (field.getType().equals(LocalDate.class)) {
                    LocalDate s = LocalDate.parse(rs.getString(columnInfo.name()));
                    type.getMethod(setterName, LocalDate.class).invoke(entity, s);
                } else {
                    int s = rs.getInt(columnInfo.name());
                    type.getMethod(setterName, field.getType()).invoke(entity, s);
                }
            } else if (field.isAnnotationPresent(Id.class)) {
                String setterName = "set" + (field.getName().charAt(0) + "").toUpperCase() + field.getName().substring(1);
                type.getMethod(setterName, int.class).invoke(entity, id);
            }
        }
        return entity;
    }

    @Override
    public <T> boolean persist(T entity) throws IllegalAccessException, SQLException {

        Field idField = getIdFieldFromEntity(entity);
        idField.setAccessible(true);
        int id = (int) idField.get(entity);
        System.out.println(id);

        if (id == 0) {
            return doInsert(entity);
        }
        return doUpdate(id, entity);

    }

    @Override
    public <T> boolean delete(T entity) throws IllegalAccessException, SQLException {

        Field fieldId=getIdFieldFromEntity(entity);
        fieldId.setAccessible(true);
        int id = (int) fieldId.get(entity);


        String query = String.format("DELETE from %s WHERE id=?", getTableName(entity));
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);


        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public <T> boolean alterTable(T entity) throws SQLException {
        String query = String.format("ALTER TABLE %s ADD COLUMN %s", getTableName(entity),getNewFields(entity.getClass()));
        return connection.prepareStatement(query).execute();
    }

    private String getNewFields(Class<?> aClass) {


    }

    private <T> boolean doUpdate(int id, T entity) throws SQLException {
        StringBuffer sb = new StringBuffer();

        Arrays.stream(entity.getClass().getDeclaredFields()).filter(f -> f.isAnnotationPresent(Column.class))
                .forEach(f -> {
                    String fieldName = f.getAnnotation(Column.class).name();
                    String fieldValue = null;
                    try {
                        fieldValue = getValuesToString(f, entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    sb.append(fieldName).append(" =").append(fieldValue).append(", ");
                });


        String query = String.format("UPDATE %s SET %s WHERE id=?", getTableName(entity), sb.toString().substring(0,sb.length()-2));
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);

        return preparedStatement.executeUpdate()>0;
    }

    private <T> boolean doInsert(T entity) throws SQLException {
        String tableName = getTableName(entity);
        String fieldNAmes = getFieldNames(entity.getClass());
        String fieldValues = getFieldValuesAsString(entity);

        String query = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, fieldNAmes, fieldValues);

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate() > 0;
    }

    private <T> String getFieldValuesAsString(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    try {
                        return getValuesToString(f, entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.joining(", "));
    }

    private <T> String getValuesToString(Field field, T entity) throws IllegalAccessException {
        String type = field.getAnnotation(Column.class).columnDefinition();
        field.setAccessible(true);

        if (type.equals("DATE") || type.startsWith("VARCHAR")) {
            try {
                return String.format(" '%s' ", field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return String.format(" %s ", field.get(entity));
    }

    private String getFieldNames(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));
    }

    private <T> String getTableName(T entity) {
        return entity.getClass().getAnnotation(Entity.class).tableName();
    }

    private <T> Field getIdFieldFromEntity(T entity) {

        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity doesn't have id"));
    }
}
