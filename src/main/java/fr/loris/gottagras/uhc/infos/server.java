package fr.loris.gottagras.uhc.infos;

public enum server {
    MAX_PLAYERS(20),
    NUMBER_OF_TEAM(20),
    PLAYER_PER_TEAM(1);

    private int value;

    server(int i) {
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
