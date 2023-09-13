var exec = require('cordova/exec');

module.exports = {
  download: function (url, dir, name, onSuccess, onError) {
    if (!url || !dir || !name) {
      onError('download args is null!');
      return;
    }
    exec(onSuccess, onError, 'SimpleDownloader', 'download', [url, dir, name]);
  },
};
