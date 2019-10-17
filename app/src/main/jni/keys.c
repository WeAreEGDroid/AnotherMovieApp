#include <jni.h>

//JNIEXPORT jstring JNICALL
//Java_com_egdroid_movieapp_MainActivity_getNativeKey1(JNIEnv *env, jobject instance) {
//
// return (*env)->  NewStringUTF(env, "ODI4MTU3MDk1YTU0ZWQ4ZjY4Yjg4ODI2MjRlZDc2NjA=");
//}

JNIEXPORT jstring JNICALL
Java_com_egdroid_datasource_remote_movie_MoviesServiceFactory_getNativeKey(JNIEnv *env,
                                                                           jobject instance) {

    return (*env)->NewStringUTF(env, "ODI4MTU3MDk1YTU0ZWQ4ZjY4Yjg4ODI2MjRlZDc2NjA=");
}