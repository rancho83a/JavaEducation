package bg.softuni.hateoas.config;

import bg.softuni.hateoas.model.dto.OrderDto;
import bg.softuni.hateoas.model.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default OrderDto mapEntityToDto(OrderEntity order) {

        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setStudentId(order.getStudent().getId());
        orderDto.setCourseId(order.getCourse().getId());

        return orderDto;
    }
}
