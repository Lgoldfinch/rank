package models

trait ResponseToAvailableTaxis[A <: AvailableTaxi] {
  val taxis: Set[A]
}

trait AvailableTaxi