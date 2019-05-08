package View;


interface ProgressListener {
    enum AppState {
        PRESERVICE,SERVICE,POSTSERVICE,MENU,SERVICESTATE,AUNTHENTICATION,LAUNCHER
    }
    void progressFrom(AppState currentState);
}
