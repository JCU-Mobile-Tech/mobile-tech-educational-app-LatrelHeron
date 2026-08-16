[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/fj45pCZN)

# Maths Practice

Maths Practice is an Android educational application designed to help younger students practise multiplication and division skills through short interactive quizzes.

The application provides different difficulty levels, immediate answer feedback, progress tracking, and a rank system to encourage continued practice.

## Features

- 10-question mixed multiplication and division quizzes
- Easy, Normal, and Hard difficulty levels
- Multiple-choice questions with immediate correct/incorrect feedback
- Quick Practice mode for practice without affecting statistics
- Persistent quiz statistics and accuracy tracking
- Rank progression based on completed sessions
- User settings including quiz difficulty and sound preferences
- Online maths verification after completing a quiz
- Navigation between Home, Activity, Rank, and Settings screens

## Difficulty Levels

The user can select a difficulty level from the Settings screen:

- **Easy:** Numbers 2–5
- **Normal:** Numbers 2–12
- **Hard:** Numbers 2–20

The selected difficulty is stored and used when generating future quiz questions.

## Easy:

<p align="center">
  <img src="screenshots/easy.png" width="220">
  <img src="screenshots/easy_landing.png" width="220">
  <img src="screenshots/easy_act.png" width="220">
</p>

## Normal:

<p align="center">
  <img src="screenshots/normal.png" width="220">
  <img src="screenshots/normal_landing.png" width="220">
  <img src="screenshots/normal_act.png" width="220">
</p>

## Hard:

<p align="center">
  <img src="screenshots/settings.png" width="220">
  <img src="screenshots/Hard_landing.png" width="220">
  <img src="screenshots/Hard_act.png" width="220">
</p>

## Rank System

The application includes a simple gamified rank system based on the number of completed quiz sessions.

- Unranked - less than 10 sessions
- Bronze – 10 sessions
- Silver – 25 sessions
- Gold – 50 sessions
- Diamond – 75 sessions
- Netherite – 100 sessions

The user's current rank and progress towards the next rank are displayed on the Rank screen.

## All the Ranks that can be achieved:

<p align="center">
<img src="screenshots/rank_unranked.png" width="220">
<img src="screenshots/rank_bronze.png" width="220">
<img src="screenshots/rank_sliver.png" width="220">
<img src="screenshots/rank_gold.png" width="220">
<img src="screenshots/rank_gold.png" width="220">
<img src="screenshots/rank_gold.png" width="220">
<img src="screenshots/rank_netherite.png" width="220">
</p>

## Reset rank option but at a cost
Also the user can reset statistics at the cost of there rank
<img src="screenshots/rank_progressrestbutton.png" width="220">
<img src="screenshots/rank_resetc.png" width="220">


## Application Screens

### Home/Landing

Displays the user's current accuracy and completed sessions. The user can start a quiz or enter Quick Practice.
<p align="center">
<img src="screenshots/home_1.png" width="220">
<img src="screenshots/home_2.png" width="220">
</p>

## Activity Emulation (practice quiz)

Is a emmulation of what the user can expect before taking a real quiz in the activity screen, in which it generates a single question based on diffucity selected for both multiplcation and division.
<p align="center">
  <img src="screenshots/prac_1.png" width="220">
  <img src="screenshots/prac_2.png" width="220">
  <img src="screenshots/prac_complete.png" width="220">
</p>

### Activity

Runs the main 10-question quiz. Questions consist of multiplication and division problems with four possible answers. The application provides immediate visual feedback after an answer is submitted. Once the quiz is complete API is used to check answers and verify correct.

## the quiz flow
<p align="center">
<img src="screenshots/activity_unanswered.png" width="220">
<img src="screenshots/activity_answerbeforesubmission.png" width="220">
<img src="screenshots/activity_answersubmissioncorrect.png" width="220">
<img src="screenshots/activity_answersubmissionwrong.png" width="220">
<img src="screenshots/activity_completedquiz.png" width="220">
</p>

### Rank

Displays overall statistics, completed sessions, multiplication and division accuracy, current rank, and rank milestones.

### Settings

Allows the user to change the quiz difficulty and answer feedback sound preference.
<p align="center">
<img src="screenshots/settings.png" width="220">
<img src="screenshots/sound_enabled.png" width="220">
</p>

## Data Storage

The application uses **Room** for persistent storage of completed quiz attempts and statistics.

**Preferences DataStore** is used for lightweight user settings such as difficulty and sound preferences.

This allows progress and settings to remain available between application sessions.

## Online Maths Check

The application uses **Retrofit** to communicate with an external maths service.

After a quiz is completed, a mathematical expression is sent to the service and the returned result is displayed as an online verification.

Network errors are handled so that failure of the online service does not prevent the user from completing the main quiz.

<img src="screenshots/activity_completedquiz.png" width="220">

## Architecture

The application separates UI, application logic, and data access using:

- Jetpack Compose UI
- ViewModels
- Repository classes
- Room database
- Preferences DataStore
- Retrofit
- Kotlin Coroutines and Flow

A simple dependency container is used to provide repositories and other application dependencies.

## Testing

Unit tests are used to test application logic independently from the user interface.

Testing includes the `ScoreCalculator`, including:

- Overall quiz accuracy
- Multiplication accuracy
- Division accuracy

## Technologies

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Navigation Compose
- Room
- Preferences DataStore
- Retrofit
- Kotlin Coroutines
- Kotlin Flow
- JUnit

## Known Limitations

- Online maths verification requires an internet connection.
- If the external maths service is unavailable, the main quiz remains functional but online verification cannot be displayed.

## Assessment

CP3406 – Mobile Computing  
Assessment 3 – Education App
