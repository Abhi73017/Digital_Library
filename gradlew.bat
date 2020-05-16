<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/general_background"
    tools:context=".teacher.trainer_dashbaord">

    <Button
        android:id="@+id/upload_notes_btn"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="@drawable/btn_back1"
        android:fontFamily="@font/bold"
        android:gravity="center_vertical"
        android:text="    Upload Notes"
        android:textAllCaps="false"
        android:textColor="@color/blue"
        android:textSize="25dp"
        app:layout_constraintBottom_toTopOf="@+id/guideline22"
        app:layout_constraintEnd_toEndOf="@id/guideline17"
        app:layout_constraintStart_toStartOf="@id/guideline16"
        app:layout_constraintTop_toTopOf="@+id/guideline21" />

    <Button
        android:id="@+id/upload_assign_btn"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="@drawable/btn_back1"
        android:fontFamily="@font/bold"
        android:gravity="center_vertical"
        android:text="    Upload Assignment"
        android:textAllCaps="false"
        android:textColor="@color/blue"
        android:textSize="25dp"
        app:layout_constraintBottom_toTopOf="@+id/guideline24"
        app:layout_constraintEnd_toEndOf="@id/guideline17"
        app:layout_constraintStart_toStartOf="@id/guideline16"
        app:layout_constraintTop_toTopOf="@+id/guideline23" />

    <Button
        android:id="@+id/check_assign_btn"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="@drawable/btn_back1"
        android:fontFamily="@font/bold"
        android:gravity="center_vertical"
        android:text="    Check Assignments"
        android:textAllCaps="false"
        android:textColor="@color/blue"
        android:textSize="25dp"
        app:layout_constraintBottom_toTopOf=