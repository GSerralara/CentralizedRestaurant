package View;

/**
 * Interface that will implent the frame of the ui
 */

interface ProgressListener {
    enum AppState {
        PRESERVICE,SERVICE,POSTSERVICE,SERVICESTATE,AUNTHENTICATION,LAUNCHER,WELCOME,REGISTER
    }

    /**
     * procedure of the view
     * @param currentState that represents the upcoming view
     */
    void progressFrom(AppState currentState);
}
