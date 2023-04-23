package exercise19;

class CombinationLock
{
    private int [] combination;
    public String status;

    public CombinationLock(int[] combination)
    {
        this.combination = combination;
    }

    public void enterDigit(int digit)
    {
        // todo: check digit and update status here
    }
}

class CombinationLockSolution
{
    private int [] combination;

    public String status;
    private int digitsEntered = 0;
    private boolean failed = false;

    public CombinationLockSolution(int[] combination)
    {
        this.combination = combination;
        reset();
    }

    private void reset()
    {
        status = "LOCKED";
        digitsEntered = 0;
        failed = false;
    }

    public void enterDigit(int digit)
    {
        if (status.equals("LOCKED")) status = "";
        status += digit;
        if (combination[digitsEntered] != digit)
        {
            failed = true;
        }
        digitsEntered++;

        if (digitsEntered == combination.length)
            status = failed ? "ERROR" : "OPEN";
    }
}
