class HttpRequest {

    public send (controllerMethod: string, data: any = []) : Promise<object> {
        return new Promise ((resolve, reject) => {
            let xhr: XMLHttpRequest = new XMLHttpRequest();
            xhr.open("POST", "terrain/" + controllerMethod);
            xhr.setRequestHeader('X-CSRF-TOKEN', document.getElementsByTagName('meta')[0].content);
            xhr.onload = function () {
                resolve(JSON.parse(xhr.responseText));
            };
            xhr.send(data);
        })
    }

}