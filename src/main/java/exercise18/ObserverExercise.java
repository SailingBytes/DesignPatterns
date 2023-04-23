package exercise18;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

class EventOrig<T> {
  public String name;
  private List<BiConsumer<Object, T>> consumers = new ArrayList<>();

  public EventOrig(String s) {
    this.name = s;
  }

  public void subscribe(BiConsumer<Object, T> consumer)
  {
    consumers.add(consumer);
  }

  public void invoke(Object sender, T arg)
  {
    for (BiConsumer<Object, T> consumer : consumers){
//      System.out.println("invoke call on event \"" + name + "\" with args: sender = " + sender + " args = " + (arg != null ? arg : " noArgs"));
      consumer.accept(sender, arg);
//      System.out.println();
    }
  }
}

class GameOrig
{
  public EventOrig<RatOrig> ratEnters = new EventOrig<>("ratEnters");
  public EventOrig<RatOrig> ratDies = new EventOrig<>("ratDies");
  public EventOrig<RatOrig> notifyRat = new EventOrig<>("notifyRat");
}

class RatOrig implements Closeable
{
  private GameOrig GameOrig;

  private String name;
  public int attack = 1;

  public RatOrig(GameOrig GameOrig, String name)
  {
    this.GameOrig = GameOrig;
    this.name = name;
    System.out.println("Hi! This is newly created RatInstance " + name +"!");

    GameOrig.ratEnters.subscribe((caller, arg) -> {
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println(">>>ratEnters<<< begins for RatInstance = " + this.name);
      System.out.println("caller.name = " + caller.toString() + "; Am I the newly joined rat? " + (this != caller ? "nope" : "It's me!!!"));

      if (caller != this)
      {
        increaseAp(name);
        GameOrig.notifyRat.invoke(this, (RatOrig) caller); // call notify rat an ALL registered rats!
      }

      System.out.println(">>>ratEnters<<< ended for RatInstance = " + this.name);
      System.out.println("--------------------------------------------------------------------------------------------------------");
    });

    GameOrig.notifyRat.subscribe((caller, rat) -> {
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println(">>>notifyRat<<< begins for RatInstance = " + this.name);
      System.out.println("caller.name = " + caller.toString() + "; param = " + rat.toString() + "; Newly joined rat is me? " + (rat == this));

      if (rat == this) {
        increaseAp(name);
      }

      System.out.println(">>>notifyRat<<< ended for RatInstance = "+this.name);
      System.out.println("---------------------------------------------------------------------------------------------------------");
    });

    GameOrig.ratDies.subscribe((caller, arg) -> {
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println(">>>ratDies<<< begins. RatInstance = " + this.name);
      System.out.println("caller.name = " + caller.toString());

      --attack;

      System.out.println(">>>ratDies<<< ended for RatInstance = "+this.name);
      System.out.println("------------------------------------------------------------------------------------------------------------");
    });

    System.out.println(name + " enters the game!");
    System.out.println();
    GameOrig.ratEnters.invoke(this, null);
  }

  private void increaseAp(String name) {
    System.out.println("increase attack by 1 for " + name);
    ++attack;
  }

  @Override
  public void close() throws IOException
  {
    // RatOrig dies ;(
    GameOrig.ratDies.invoke(this, null);
  }

  @Override
  public String toString() {
    return name;
  }
}