var exec = require('cordova/exec');

module.exports = {
  download: function (url, dest, onSuccess, onError) {
    if (!url || !dest) {
      console.error('download url or dest is null!');
      return;
    }
    exec(onSuccess, onError, 'SimpleDownload', 'download', [url, dest]);
  },
};
