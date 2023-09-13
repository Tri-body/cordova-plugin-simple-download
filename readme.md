## cordova-plugin-simple-download

a simple http download file cordova plugin for android platfrom.

### Install/UnInstall

install from github repository using this command

```sh
cordova plugin add https://github.com/Tri-body/cordova-plugin-simple-download 
```

uninstall using this command

```sh
cordova plugin remove cordova-plugin-simple-download
```

### How to Use

```js
    SimpleDownload.download(
      'http://127.0.0.1:8080/xxx.mp4',
      "/storage/emulated/0/Android/data/<appid>/files/",  // can't use file:///storage...
      'yyy.mp4',
      () => {
        console.log('ok');
      },
      e => {
        console.error('error: ', e);
      }
    );
```