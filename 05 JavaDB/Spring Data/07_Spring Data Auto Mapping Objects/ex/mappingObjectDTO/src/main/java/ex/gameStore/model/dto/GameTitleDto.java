package ex.gameStore.model.dto;

public class GameTitleDto {
    private String title;

    public GameTitleDto() {
    }

    public GameTitleDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
