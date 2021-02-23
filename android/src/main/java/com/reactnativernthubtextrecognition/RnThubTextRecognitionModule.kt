package com.reactnativernthubtextrecognition

import android.net.Uri
import com.facebook.react.bridge.*
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import java.io.File
import java.io.IOException

class RnThubTextRecognitionModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  private var textDetectionSuccessCallback: Callback? = null
  private var textDetectionFailureCallback: Callback? = null

  val recognizer = TextRecognition.getClient()

  override fun getName(): String {
    return "RnThubTextRecognition"
  }

  @ReactMethod
  fun textDetection(uri: String, successCallback: Callback, failureCallback: Callback) {
    textDetectionSuccessCallback = successCallback
    textDetectionFailureCallback = failureCallback

    val image: InputImage
    try {
      val file = File(uri)
      image = InputImage.fromFilePath(reactContext, Uri.parse(file.toString()))
      val result = recognizer.process(image)
        .addOnSuccessListener { result ->
          val resultText = result.text
          val builder = StringBuilder()
          for (block in result.textBlocks) {
            val blockText = block.text
            builder.append(blockText)
//            val blockCornerPoints = block.cornerPoints
//            val blockFrame = block.boundingBox
            for (line in block.lines) {
              val lineText = line.text
              builder.append(lineText)
//              val lineCornerPoints = line.cornerPoints
//              val lineFrame = line.boundingBox
              for (element in line.elements) {
                val elementText = element.text
                builder.append(elementText)
//                val elementCornerPoints = element.cornerPoints
//                val elementFrame = element.boundingBox
              }
            }
          }

          textDetectionSuccessCallback?.invoke("$resultText $builder")
        }
        .addOnFailureListener { e ->
          textDetectionFailureCallback?.invoke("${e.message.toString()}, Could not read text from image")
        }
    } catch (e: IOException) {
      e.printStackTrace()
      textDetectionFailureCallback?.invoke(e.message.toString())
    }
  }


}
