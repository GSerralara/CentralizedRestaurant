package View;


interface ProgressListener {
    enum AppState {
        LAUNCHER,REGISTER,MAINTABLE,MENU,BOOK,CANCEL
    }
    void progressFrom(AppState currentState);
}
