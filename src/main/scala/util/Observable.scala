package util

class Observable {
  private var observers = Vector.empty[Observer]

  def add(observer: Observer): Unit = {
    observers = observers :+ observer
  }

  def remove(observer: Observer): Unit = {
    observers = observers.filterNot(_ == observer)
  }

  def notifyObservers(): Unit = {
    observers.foreach(_.update())
  }
}
