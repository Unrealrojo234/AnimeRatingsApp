# Art Space Anime Gallery

A Jetpack Compose Android app showcasing a curated collection of anime series in an interactive carousel format. Users can navigate through different anime titles, view ratings, and read descriptions.

<img src="https://github.com/user-attachments/assets/1627ae43-36f0-4abf-ba40-c56d66b25f94" width="300"/>


## Features

🖼️ **Anime Carousel**  
- Displays high-quality anime artwork
- Shows title, star-based ratings, and detailed descriptions
- Responsive card design with rounded corners

🎚️ **Interactive Navigation**  
- Previous/Next buttons for sequential navigation
- Auto-disable buttons at list boundaries
- Smooth index-based state management

✨ **Modern UI**  
- Material Design 3 components
- Custom typography and color schemes
- Scrollable content areas
- Decorative dividers and spacing

## Installation

1. Clone repository:
   ```bash
   git clone https://github.com/Unrealrojo234/AnimeRatingsApp
   ```
2. Open project in Android Studio (Arctic Fox or newer)
3. Build and run on emulator/device (API 24+)

## Usage

1. Launch the app to see the featured anime
2. Use bottom buttons to navigate:
   - **Previous**: Move to previous entry
   - **Next**: Move to next entry
3. Scroll vertically for long descriptions

## Code Structure

### Key Components

- **MainActivity**: Entry point with edge-to-edge Scaffold
- **ArtGalleryApp**: Root composable with state management
- **Carousel**: Main display card component
- **ActionButton**: Custom styled navigation button
- **SharedState**: CompositionLocal state management

### Data Model

```kotlin
val animes = arrayOf(
    arrayOf(R.drawable.highschooldxd, "HighSchool DxD", "9.7", R.string.highdchooldxd),
    // ... other entries
)
```

### State Management

```kotlin
// Shared index state
data class SharedState(
    val value: Int,
    val updateValue: (Int) -> Unit
)

// Local composition provider
val LocalSharedState = staticCompositionLocalOf<SharedState> { ... }
```

## Technologies

- 100% Kotlin
- Jetpack Compose
- Material Design 3
- CompositionLocal state
- Resource management (strings, colors, drawables)

## Customization

To add more anime entries:

1. Add new drawable resources
2. Create string resources for descriptions
3. Extend `animes` array:
   ```kotlin
   arrayOf(R.drawable.new_image, "Title", "Rating", R.string.new_description)
   ```

## Future Enhancements

- Add favorites system
- Implement swipe gestures
- Add search functionality
- Include video previews
- Expand rating system with user reviews

## Credits

Anime artwork and descriptions courtesy of their respective copyright holders.  
App developed by **Ryan** following Android best practices.

---

**License**: MIT  
**Version**: 1.0.0  
**Updated**: Jan 25th 2025
