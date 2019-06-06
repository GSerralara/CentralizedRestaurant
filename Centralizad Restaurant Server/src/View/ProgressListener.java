package View;


interface ProgressListener {
    enum AppState {
        PRESERVICE,SERVICE,POSTSERVICE,SERVICESTATE,AUNTHENTICATION,LAUNCHER,WELCOME,REGISTER
    }
    void progressFrom(AppState currentState);
}
