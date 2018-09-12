
# imageloader使用手册

首先，在根build.gradle中添加

allprojects {

    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
        maven { url 'https://maven.google.com' }
    }
}

然后在app的build.gradle下添加

dependencies {

   	 implementation fileTree(include: ['*.jar'], dir: 'libs')
   	 implementation 'com.github.hrj981671454:FrameDesignProject:1.0.8'
}

在app的Application中配置

    private void initImageLoader() {
        ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder(this);
        build.setThreadCount(3) //线程数量
                .setLoadPolicy(new ReversePolicy()) //加载策略，倒序、正序
                .setCachePolicy(new DoubleCache(this)) //缓存策略 磁盘、内存缓存或者内存缓存
                .setLoadingPlaceHolder(R.mipmap.ic_launcher)//默认展示加载中图片，全局应保持统一
                .setFailedPlaceHolder(R.mipmap.ic_launcher)//默认加载失败图片，全局应保持统一
                .setImageCachePath(getImageCachePath())//设置图片缓存位置，必传
                .setImageCacheSize(50 * 1024 * 1024);//设置缓存大小、必传
        ImageLoaderConfig config = build.build();
        //初始化
        imageLoader = SimpleImageLoader.getInstance(config);
        //设置图片加载器，如不传，则使用本人自定义的图片加载器
        
		  //config.setImageLoader(new YAJFrescoImageLoader(this, null));

		 //config.setImageLoader(new YAJPicassoImageLoader(this, null));

        config.setImageLoader(new YAJGlideImageLoader(this));//设置图片加载器，如不传，则使用本人自定义的图片加载器
       支持目前流行的Glide、Fresco、Picasso
    }
