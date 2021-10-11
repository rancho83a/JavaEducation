public enum Season {
    AUTUMN(1),
    SPRING(2),
    WINTER(3),
    SUMMER(4);

    private int koef;

    Season(int koef){
        this.koef=koef;
    }

    public int getKoef() {
        return koef;
    }
}
