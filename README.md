# FrameDesignProject
使用步骤
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
app.build
dependencies {
	implementation 'com.github.hrj981671454:FrameDesignProject:1.0.0'
}