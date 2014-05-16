## ShopLove iOS Tracking SDK
================

### 1. Installation
There are only a few steps to integrate the ShopLove Tracking Service into your project.

#### Get the ShopLoveTracking SDK
Download the latest release from the [releases page][releases] and unzip the archive. Assuming that you’re using Android Studio as your favorite IDE, simply add the subdirectory SLTTracking to the root folder of your project.



### 2. Integrate ShopLoveTracking into your app
 
To notify your app about the new module, navigate to your settings.gradle file of your project and add this line

```gradle
include ':SLTTracking'
```

In order that the ShopLoveTracking module gets compiled as well, open the build.gradle file of your app and place this line 


```gradle
dependencies {
	…
    	compile project(':SLTTracking')
	…
}
```

After synchronizing the gradle files open your main activity source file and add this line at the top

```java
import com.shoplove.tracking.ShopLoveTracking;
```

and in the `onCreate` method

```java
ShopLoveTracking.appDidLaunch(this, "{YourAppToken}");
```

Replace `{YourAppToken}` with your App Token.

Your Activity source file should now look something like this

```java
package com.company.yourapp;

import android.os.Bundle;

import com.shoplove.tracking.ShopLoveTracking;


public class MainActivity extends Activity {
…
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShopLoveTracking.appDidLaunch(this, "{YourAppToken}");
    }
…
}

```




[releases]: https://github.com/ShopLove/tracking-sdk-android/releases



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