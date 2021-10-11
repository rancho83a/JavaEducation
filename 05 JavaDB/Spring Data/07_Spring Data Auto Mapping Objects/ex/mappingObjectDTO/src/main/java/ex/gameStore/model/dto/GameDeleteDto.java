package ex.gameStore.model.dto;

import javax.validation.constraints.Min;

public class GameDeleteDto {
    private Long id;

    @Min(1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDeleteDto() {
    }

    public GameDeleteDto(Long id) {
        this.id = id;
    }
}
