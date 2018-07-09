package org.codetome.kurator

import org.codetome.kurator.Playground.Tree.*
import java.util.*

object Playground {

    @JvmStatic
    fun main(args: Array<String>) {

        val tree = Node(
                Leaf("Foo"),
                Node(Leaf("baz"), Empty))

    }

    sealed class Tree<out T> {
        class Node<T>(val left: Tree<T>, val right: Tree<T>) : Tree<T>()
        class Leaf<T>(val value: T) : Tree<T>()
        object Empty : Tree<Nothing>()
    }
}
