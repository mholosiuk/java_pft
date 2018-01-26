package ru.stqa.pft.sandbox1;

public class Processor {

  public double calcSpeed;
  public int coresCount;

  public Processor(double calcSpeed, int coresCount) {
    this.calcSpeed = calcSpeed;
    this.coresCount = coresCount;
  }

  public void showData () {

    System.out.println(this.calcSpeed);
    System.out.println(coresCount);
  }

}
