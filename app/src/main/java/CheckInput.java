public class CheckInput {
    private String theErrorMessage;
    private boolean theState;

    public CheckInput(String theString, int mode) {
        theErrorMessage = "";
        theState = true;
        if(mode == 0) {
            checkEmail(theString);
        }else if(mode == 1) {
            checkPassword(theString);
        }
    }

    private void checkPassword(String theString) {
        if(!checkForEmptyPassword(theString)) {
            theErrorMessage.concat("Invalid Password: password is empty\n");
            theState = false;
        }
        if(!checkForShortPassword(theString)) {
            theErrorMessage.concat("Invalid Password: password is to short");
            theState = false;
        }
    }

    private void checkEmail(String theString) {
        if(!checkForAtEmail(theString)) {
            theErrorMessage.concat("Invalid Email: contains no @\n");
            theState = false;
        }
        if(!checkForEmptyEmail(theString)) {
            theErrorMessage.concat("Invalid Email: email is empty");
            theState = false;
        }

    }

    public String getErrorMessage() {
        return theErrorMessage;
    }
    public boolean getState() {
        return theState;
    }

    private boolean checkForAtEmail(String email) {
        boolean state = true;
        if(!email.contains("@")) {
            state = false;
        }
        return state;
    }
    private boolean checkForEmptyEmail(String email) {
        boolean state = true;
        if(email.trim().isEmpty()) {
            state = false;
        }
        return state;
    }
    private boolean checkForEmptyPassword(String password) {
        boolean state = true;
        if(password.trim().isEmpty()) {
            state = false;
        }
        return state;
    }

    private boolean checkForShortPassword(String password) {
        boolean state = true;
        if(password.trim().length() < 6) {
            state = false;
        }
        return state;
    }


}
