package com.sksamuel.scapegoat.goat

import com.sksamuel.scapegoat.{Reporter, Inspection}

import scala.reflect.runtime._
import scala.reflect.runtime.universe._

/** @author Stephen Samuel */
object OptionGetInspection extends Inspection {
  val optionSymbol = rootMirror.staticClass("scala.Option")
  override def traverser(reporter: Reporter) = new universe.Traverser {
    override def traverse(tree: scala.reflect.runtime.universe.Tree): Unit = {
      tree match {
        case Select(left, TermName("get")) =>
          if (left.tpe.typeSymbol.fullName == optionSymbol.asType.fullName) reporter.warn("Use of Option.get")
        case _ => super.traverse(tree)
      }
    }
  }
}