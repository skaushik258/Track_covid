package Model;

public class State_Model {

    String State;
    String State_cases;
    String State_Recovered;
    String State_Active;
    String State_Death;
    String State_Today_Recovered;
    String State_Today_positive;

    public State_Model(String state, String state_cases, String state_Recovered,
                       String state_Active, String state_Death, String state_Today_Recovered, String state_Today_positive) {
        State = state;
        State_cases = state_cases;
        State_Recovered = state_Recovered;
        State_Active = state_Active;
        State_Death = state_Death;
        State_Today_Recovered = state_Today_Recovered;
        State_Today_positive = state_Today_positive;


    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getState_cases() {
        return State_cases;
    }

    public void setState_cases(String state_cases) {
        State_cases = state_cases;
    }

    public String getState_Recovered() {
        return State_Recovered;
    }

    public void setState_Recovered(String state_Recovered) {
        State_Recovered = state_Recovered;
    }

    public String getState_Active() {
        return State_Active;
    }

    public void setState_Active(String state_Active) {
        State_Active = state_Active;
    }

    public String getState_Death() {
        return State_Death;
    }

    public void setState_Death(String state_Death) {
        State_Death = state_Death;
    }

    public String getState_Today_Recovered() {
        return State_Today_Recovered;
    }

    public void setState_Today_Recovered(String state_Today_Recovered) {
        State_Today_Recovered = state_Today_Recovered;
    }

    public String getState_Today_positive() {
        return State_Today_positive;
    }

    public void setState_Today_positive(String state_Today_positive) {
        State_Today_positive = state_Today_positive;
    }


}
