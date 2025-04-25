# Lokal Intern Assessment App

This is a simple job listing Android application built as part of an assignment. It showcases how to build a scalable, modular application using clean architecture principles and modern Android libraries.

## ✨ Features

- Bottom navigation UI with **Jobs** and **Bookmarks** sections.
- **Jobs Screen** fetches data using **infinite scroll** from a remote API.
- Each job card shows:
  - Job Title
  - Location
  - Salary
  - Phone Number
- Clicking a job navigates to a **detail screen** with full information.
- Users can **bookmark** jobs to view them later in the **Bookmarks** section.
- Bookmarked jobs are saved **offline** using Room database.
- Proper UI **states** handled:
  - Loading
  - Error
  - Empty

---

## 🧱 Architecture

This app is built using the **MVVM architecture** pattern for clear separation of concerns and testability.

### 🔧 Tech Stack

- **MVVM** – clean separation of View, ViewModel, and Model layers.
- **Retrofit** – for API calls.
- **Room** – for local storage of bookmarked jobs.
- **Hilt** – for dependency injection.
- **StateFlow** – for reactive UI updates.
- **Navigation Component** – for handling screen navigation.

---

## 🧪 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Dark-Coders995/LokalInternTask.git
   cd LokalInternTask
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync and install required dependencies.
4. Run the app on an emulator or physical device with internet access.

---

## 🎥 Demo Video

👉 [Click here to watch the demo on Google Drive](https://drive.google.com/file/d/1xWDnv2vMjwJtIUxJDIvwXeAiAVemHja3/view?usp=sharing)

---

## ✅ Requirements Covered

| Requirement                                    | Status  |
|------------------------------------------------|---------|
| Bottom Navigation (Jobs, Bookmarks)            | ✅      |
| Infinite Scroll in Jobs                        | ✅      |
| Job Details Screen                             | ✅      |
| Bookmark Functionality                         | ✅      |
| Offline Storage of Bookmarks                   | ✅      |
| Proper Loading/Error/Empty State Management    | ✅      |

---

## 📌 Notes

- All data is managed reactively using Kotlin Flow / Corountines.
- Retrofit handles network API integration with clean error handling.
- Room database persists bookmarked jobs even after app restart.
