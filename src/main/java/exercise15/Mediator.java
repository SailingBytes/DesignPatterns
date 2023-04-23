package exercise15;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    Mediator mediator;
    int value = 0;

    public Participant(Mediator mediator)
    {
        this.mediator = mediator;
        this.mediator.join(this);
    }

    public void say(int n)
    {
        mediator.say(this, n);
    }

    public void said(int n) {
        value = n;
    }
}

class Mediator
{
    List<Participant> participants = new ArrayList<>();

    public void join(Participant participant) {
        participants.add(participant);
    }

    public void say(Participant source, int n) {
        for (Participant p : participants) {
            if(!p.equals(source)) p.said(n);
        }
    }
}
