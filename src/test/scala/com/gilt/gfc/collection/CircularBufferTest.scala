package com.gilt.gfc.collection

import org.scalatest.testng.TestNGSuite
import org.scalatest.matchers.ShouldMatchers
import org.testng.annotations.Test

class CircularBufferTest extends TestNGSuite with ShouldMatchers {

  @Test
  def testBasics() {
    val buffer = new CircularBuffer[Int](5)

    buffer.toIterator.toList should equal(List())
    buffer.size should equal(0)
    evaluating { buffer.oldest } should produce[AssertionError]
    evaluating { buffer.newest } should produce[AssertionError]

    buffer.add(1)
    buffer.toIterator.toList should equal(List(1))
    buffer.size should equal(1)
    buffer.oldest should  equal(1)
    buffer.newest should equal(1)

    buffer.add(2)
    buffer.toIterator.toList should equal(List(1,2))
    buffer.size should equal(2)
    buffer.oldest should  equal(1)
    buffer.newest should equal(2)

    buffer.add(3)
    buffer.toIterator.toList should equal(List(1,2,3))
    buffer.size should equal(3)
    buffer.oldest should  equal(1)
    buffer.newest should equal(3)

    buffer.add(4)
    buffer.toIterator.toList should equal(List(1,2,3,4))
    buffer.size should equal(4)
    buffer.oldest should  equal(1)
    buffer.newest should equal(4)

    buffer.add(5)
    buffer.toIterator.toList should equal(List(1,2,3,4,5))
    buffer.size should equal(5)
    buffer.oldest should  equal(1)
    buffer.newest should equal(5)

    buffer.add(6)
    buffer.toIterator.toList should equal(List(2,3,4,5,6))
    buffer.size should equal(5)
    buffer.oldest should  equal(2)
    buffer.newest should equal(6)
  }
}
