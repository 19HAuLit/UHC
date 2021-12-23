package fr.loris.gottagras.uhc.infos;

public enum border {
    INITIAL_SIZE(Math.pow(server.MAX_PLAYERS.get(), 0.5) * 600),
    INITIAL_X(0),
    INITIAL_Z(0),
    FINAL_SIZE(100),
    FINAL_X(0),
    FINAL_Z(0);

    private double border;

    border(double size) {
        this.border = size;
    }

    public double get() {
        return border;
    }

    public void set(double size) {
        this.border = size;
    }
}
