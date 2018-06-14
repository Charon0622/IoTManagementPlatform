export default async(type = 'GET', baseUrl,  url = '', data = {}, method = 'fetch') => {
  type = type.toUpperCase();
  url = baseUrl + url;

  let queryStr = '';
  if (type === 'GET') {
    Object.keys(data).forEach(key => {
      queryStr += key + '=' + data[key] + '&';
    });

    if (queryStr !== ''){
      queryStr = queryStr.substr(0, queryStr.lastIndexOf('&'));
      url += '?' + queryStr;
    }
  }

  let dataStr = '';
  if (type === 'POST') {
    Object.keys(data).forEach(key => {
      dataStr += key + '=' + data[key] + '&';
    });

    if (dataStr !== '') {
      dataStr = dataStr.substr(0, dataStr.lastIndexOf('&'));
    }
  }

  if (window.fetch && method === 'fetch') {
    let requestConfig = {
      method: type,
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      mode: "cors",
      cache: "force-cache"
    };

    if (type === 'GET') {
      try {
        var response = await fetch(url, requestConfig);
        var responseJson = await response.json();
      }catch (error) {
        throw new error
      }
      return responseJson
    }

    if (type === 'POST') {
      return new Promise((resolve, reject) => {
        let requestObj;
        requestObj = new XMLHttpRequest();

        let sendData = dataStr;


        requestObj.open(type, url, true);
        if (method === 'multipart'){
          requestObj.setRequestHeader('Content-Type', 'multipart/form-data')
        }else {
          requestObj.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        }
        requestObj.send(sendData);

        requestObj.onreadystatechange = () => {
          if (requestObj.readyState === 4) {
            if (requestObj.status === 200) {
              let obj = requestObj.response;
              if (typeof obj !== 'object') {
                try{
                  obj = JSON.parse(obj);
                }catch (e){
                  console.log(e)
                  obj = -1
                }
              }
              resolve(obj)
            } else {
              reject(requestObj)
            }
          }
        }
      })
    }

  }else {
    return new Promise((resolve, reject) => {
      let requestObj;
      if (window.XMLHttpRequest) {
        requestObj = new XMLHttpRequest();
      } else {
        requestObj = new ActiveXObject;
      }

      let sendData = new FormData();
      if (type === 'POST') {
        // sendData = JSON.stringify(data);
        Object.keys(data).forEach(key => {
          sendData.append(key, data[key])
        });
      }

      console.log(sendData)

      requestObj.open(type, url, true);
      if (method === 'multipart'){
        var sBoundary = "---------------------------" + Date.now().toString(16);
        requestObj.setRequestHeader('Content-Type', 'multipart/form-data; boundary=' + sBoundary)
      }else {
        requestObj.setRequestHeader("content-type", "application/x-www-form-urlencoded");
      }
      requestObj.send(sendData);

      requestObj.onreadystatechange = () => {
        if (requestObj.readyState === 4) {
          if (requestObj.status === 200) {
            let obj = requestObj.response;
            if (typeof obj !== 'object') {
              try{
                obj = JSON.parse(obj);
              }catch (e){
                console.log(e)
                obj = -1
              }
            }
            resolve(obj)
          } else {
            reject(requestObj)
          }
        }
      }
    })
  }
}
