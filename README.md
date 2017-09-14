# TaggableFragmentPagerAdapter
A ViewPager fragment adapter that uses tags to correctly change data

[![Build Status](https://travis-ci.org/AlexKGwyn/TaggableFragmentPagerAdapter.svg?branch=master)](https://travis-ci.org/AlexKGwyn/TaggableFragmentPagerAdapter) [![](https://jitpack.io/v/AlexKGwyn/TaggableFragmentPagerAdapter.svg)](https://jitpack.io/#AlexKGwyn/TaggableFragmentPagerAdapter)

# Dependency
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and within your module's `build.gradle`

```gradle
dependencies {
    compile 'com.github.AlexKGwyn:TaggableFragmentPagerAdapter:insert.latest.version'
}
```

# Usage
You extend `TaggableFragmentPagerAdapter` as you would a normal fragment pager adapter, you just need to override the additional method:
```java
@Override
public String getTag(int position) {
    //return a unique tag for each fragment
    return String.valueOf(position);
}
```
See the sample `app` module for more details

License
--------

    Copyright 2017 Alex Gwyn

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.