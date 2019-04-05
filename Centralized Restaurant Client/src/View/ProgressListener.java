package View;


interface ProgressListener {
    enum AppState {
        LAUNCHER,REGISTER,MAINTABLE,MENU,BOOK,BILL,MAIN,ORDER
    }
    void progressFrom(AppState currentState);
}
