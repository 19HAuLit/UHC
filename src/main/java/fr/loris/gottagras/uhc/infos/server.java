package fr.loris.gottagras.uhc.infos;

public enum server {
    MAX_PLAYERS(20);

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
