# Augure

## What is it?
Augure is a service that '_tries its best_' to provide rich previews for web links.

## How is it useful to me?
You can use **Augure** to display contextual rich previews for web links anywhere inside your application. Recipients of the link can then check the data of the preview and ascertain whether they want to actually click the link or not. This also works the other way round in that it can pique the user's interest to open the link. 

## Usage
Ideally, you create just one instance of `Augure` across your entire application. The `Augure` class is thread-safe and so can be used between multiple threads all across your application.

#### Input -
```
String url = "http://twitter.com/imvkohli";
Augure augure = new Augure();
LinkPreview preview = augure.get(url);
System.out.println(preview.getTitle());
System.out.println(preview.getDescription());
System.out.println(preview.getThumbnailUrl());
```

#### Output -
```
Virat Kohli (@imVkohli) | Twitter
The latest Tweets from Virat Kohli (@imVkohli). The Official twitter account of Virat Kohli, Indian cricketer, gamer, car lover, loves soccer and an enthusiast
https://abs.twimg.com/favicons/favicon.ico
```

## What's with the name?
The term '_augure_' is intended to be an extension of the English word '_augur_' which means '_portend a good or bad outcome_'. **Augure** is named so because the preview can either force the user to click on the link or just completely do away with it.

## Issues/Contributions - 
Shoot a mail to dasika.sandy@gmail.com or raise a pull request.

#### Note: The code is not entirely mine and was contributed by multiple collaborators in my last workplace including me. I am just open-sourcing the code for the benefit of the community and do not claim sole leadership/responsibility of the project in any way.
