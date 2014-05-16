## ShopLove iOS Tracking SDK
================

### 1. Installation
There are only a few steps to integrate the ShopLove Tracking Service into your project.

#### Get the ShopLoveTracking SDK
Download the latest release from the [releases page][releases] and unzip the archive. Assuming that you’re using Android Studio as your favorite IDE, simply add the subdirectory SLTTracking to your libs folder of your project.



### 2. Integrate ShopLoveTracking into your app
 
To notify your app about the new module, navigate to your settings.gradle file of your project and add this line

```gradle
include ':libs:SLTTracking'
```

In order that the ShopLoveTracking module gets compiled as well, open the build.gradle file of your app and place this line 


```gradle
dependencies {
	…
    	compile project(':libs:SLTTracking')
	…
}
```

After synchronizing



Replace `{YourAppToken}` with your App Token.


[releases]: https://github.com/ShopLove/tracking-sdk-android/releases
[dragDrop0]: https://cloud.githubusercontent.com/assets/2537091/2996597/44e2dc88-dcf2-11e3-9649-219bef81f3a9.png
[dragDrop1]: https://cloud.githubusercontent.com/assets/2537091/2996598/44e39c9a-dcf2-11e3-9ee6-13a8f72997c2.png



## License

The ShopLove Tracking SDK is licensed under the MIT License.

Copyright (c) 2014 
The Visual Shopping Company GmbH (ShopLove)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.