# MTGPriceChecker

## Introduction
This is a personal project of mine to create an app that checks the price of Magic: The Gathering (MTG) trading cards.
When opening packs of MTG cards this app allows the user to quickly check the current market price of MTG cards.
Currently only Standard-legal sets are included. More cards will be added later on.

## Background
The core of this app uses Scryfall API to pull up pricing info of MTG cards, prices update daily accoding to this API. To do so, we use android volley to make some RESTful calls to interact with Scryfall.

![](app/demo/demo.gif)

First screen shows the the expansion sets that are currently in the Standard-legal format right now, and once you choose your set, it jumps right into the cards pricings in that set in USD.
Right now it shows the prices of the non-foil/regular version of the respective cards, other versions of the cards will be added soon.

## Installation
Installation can be done in Android Studio with a pull request with this repo. Compile and viola!
Deploying the app into the App store will be done at a later time when more features are added.

