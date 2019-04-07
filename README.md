# Github Stars

## Features
Users can list the most starred Github repos that were created in the last 30 days, Users can see the results as a list. One repository per row with following details:
- Repository name
- Repository description
- Numbers of stars for the repo.
- Username and avatar of the owner.
- should be able to keep scrolling and new results should appear.

## Tech used

### Android Studio/ Java
Native code, more rich of tools

### Card View and Recycler View
Easy to work with and rich
```shd
implementation 'com.android.support:recyclerview-v7:26.1.0'
implementation 'com.android.support:cardview-v7:26.1.0'
implementation 'com.android.support:design:26.1.0'
```
### Picasso
To display images in Image view from a URL
```shd
implementation 'com.squareup.picasso:picasso:2.5.2'
```
### Volley
To get the Json string from the API, its fast and secure
```shd
implementation 'com.android.volley:volley:1.1.0'
```