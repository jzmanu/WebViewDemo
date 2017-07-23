Android 开发过程中 WebView 的使用比较广泛，常用来加载网页，比如使用 WebView 加载新闻页面、使用 WebView 打开本应用的链接以及用 WebView 显示支付信息页面等，那么如何 Android 开发中如何与 WebView 中的内容进行交互呢，这种交互主要就是 Java 与 JavaScript 之间的互相调用。下面实现一下如何响应 WebView 中图片的点击事件，先来看一下实现效果：

### 显示效果
![image](http://opvs5q5qo.bkt.clouddn.com/WebViewJS.gif)

### 关键方法
#### 1. setJavaScriptEnabled()
设置 WebView 是否支持 JavaScript 脚本，默认不支持。
```java
public abstract void setJavaScriptEnabled(boolean flag);
```
#### 2. addJavascriptInterface()

 注入 Java 对象到 WebView 中，该对象将会被注入到 JavaScript 主框架的上下文中，允许使用映射的 Java 对象的名称从 JavaScript 访问该对象的方法，且只可以访问添加 @JavascriptInterface 注解的公共方法才可以从 JavaScript 中访问，可在 API level 17 以上使用这样使用。

 如果 API level 16 以及更早的 API, 所有的公共方法(包括继承的)都可以从 JavaScript 访问，可能会出现页面重新加载前，Java 对象还未注入到 JavaScript 中的情况，导致调用 Java 方法无效果。
     
*重要声明*：该方法允许 JavaScript 控制应用程序，功能非常强大，但是如果 API level 16及更早的 API 版本将会存在一定的风险,比较安全的做法是该方法尽量在 Android 4.2 及以上版本使用该方法，如果是更低的版本 JavaScript 可以使用反射来访问所注入对象的公共字段，在 WebView 中使用该方法可能会有不受信任的内容被攻击者利用，让应用程序去执行 Java 代码，要注意线程安全,Java 对象的字段不可访问，Android 5.0 以上，所注入的 Java 对象的方法是有一定数量限制的。
```java
public void addJavascriptInterface(Object object, String name) {}
```
### 具体实现
大致思路就是让 WebView 中的图片响应点击事件，然后调用 Android 界面来显示被点击的图片，实现步骤如下：
1. WebView 设置支持 JavaScript 脚本；
2. 创建与 JavaScrpt 通信的类及供 JavaScript 调用的方法；
3. 加载 WebView 中要显示的内容；
4. 使用 addJavascriptInterface 方法将 Java 对象映射到 JavaScript 中；
5. 在 JavaScript 中调用映射对象的方法，打开显示图片的 Activity;
6. 调用 javaScript 中的方法。
#### 第一步：
WebView 设置支持 JavaScript 脚本，如下：

```java
//设置支持JavaScript
webSettings.setJavaScriptEnabled(true);
```
#### 第二步：
创建与 JavaScrpt 通信的类及供 JavaScript 调用的方法，如下：

```java
/**
 * 与 javascript 通信的 Java 对象，提供 javascript 调用的方法
 * Created by jzman on 2017/7/20 0020.
 */
public class AndroidInterface {
    private Context context;
    public AndroidInterface(Context context) {
        this.context = context;
    }

    /**
     * 添加注解 @JavascriptInterface
     * javascript 要调用的方法
     */
    @JavascriptInterface
    public void showImage(String imgUrl){
        Intent intent = new Intent();
        intent.putExtra("img",imgUrl);
        intent.setClass(context,ImageActivity.class);
        context.startActivity(intent);
    }
}
```
#### 第三步：
加载 WebView 要显示的内容，这里加载 aasets 目录下的 HTML 文件，如下：

```
//加载 assets 目录下的 HTML 文件
webView.loadUrl("file:///android_asset/index.html");
```

#### 第四步：
使用 addJavascriptInterface 方法将 Java 对象映射到 JavaScript 中，如下：
```java
//注入Java对象并映射到JavaScript中
//参数(与JaveScript通信的对象，映射到JavaScript中的对象)
webView.addJavascriptInterface(new AndroidInterface(this),"imageListener");
```
#### 第五步：
在 JavaScript 中查找 <img> 标签并在其点击事件里面调用其映射对象的方法打开显示图片的 Activity,如下：

```javascript
<script type="text/javascript">
	function findImg(){
		//查找img标签
		var imgs = document.getElementsByTagName("img");
		//遍历img标签
		for(var i=0; i<imgs.length; i++){
			//为每一个标签设置点击事件
			imgs[i].onclick = function(){
			 //imageListener映射的Java对象
			window.imageListener.showImage(this.src);
			}
		}
	}
</script>
```
#### 第六步：
调用 JavaScript 里面的方法，为保证调用时 Java 对象还未注入到 JavaScript 中，应该页面加载完成时调用 javaScript 的方法，如下：

```java
//设置 WebViewClient 监听相关事件
webView.setWebViewClient(new WebViewClient(){
    //页面加载完成回调该方法
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        //保证页面加载完成后Java对象注入到JavaScript中
        webView.loadUrl("javascript:findImg()");
    }
});
```


【记录学习历程，分享点滴生活...by jzman】

<完>
