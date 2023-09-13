var exec = require('cordova/exec');

module.exports = {
  download: function (url, dir, name, onSuccess, onError) {
    if (!url || !dir || !name) {
      onError('download args is null!');
      return;
    }
    exec(onSuccess, onError, 'SimpleDownload', 'download', [url, dir, name]);
  },
};
