function $ (querySelector: any) {
    let elements = document.querySelectorAll.bind(document);
    let results = [];

    if (typeof querySelector === "object") {
        results = [querySelector];
    } else {
        results = elements(querySelector);
    }

    let currentResultIndex = -1;

    this.getIndex = function () {
        if (currentResultIndex === -1) {
            return 0;
        }

        return currentResultIndex;
    }

    this.next = function () {
        currentResultIndex++;
        return currentResultIndex < results.length;
    }

    this.length = function () {
        return results.length;
    }

    this.text = function (text: string) {
        results[this.getIndex()].innerText = text;
        return this;
    }

    this.html = function (html: string) {
        results[this.getIndex()].innerHTML += html;
        return this;
    }

    this.hide = function () {
        results[this.getIndex()].style.display = "none";
        return this;
    }

    this.show = function () {
        results[this.getIndex()].style.display = "";
        return this;
    }

    this.css = function (rules: any, value: string = "") {
        if (typeof rules === 'object') {
            for (let i in rules) {
                results[this.getIndex()].style[i] = rules[i];
            }
        } else {
            results[this.getIndex()].style[rules] = value;
        }

        return this;
    }

    this.on = function (event: string, func: any) {
        results[this.getIndex()].addEventListener(event, func);
    }

    this.removeAttr = function (attributeName: string) {
        results[this.getIndex()].removeAttribute(attributeName);
        return this;
    }

    this.attr = function (attributeName: string, value: any) {
        results[this.getIndex()].setAttribute(attributeName, value);
        return this;
    }

    this.append = function (element: any) {
        if (typeof element === "string") {
            element = document.createElement(element);
        }

        results[this.getIndex()].append(element);
        return this;
    }

    this.scrollTop = function (value: number = 0) {
        if (value > 0) {
            results[this.getIndex()].scrollTop = value;
            return this;
        }

        return results[this.getIndex()].scrollTop;
    }

    this.scrollLeft = function (value: number = 0) {
        if (value > 0) {
            results[this.getIndex()].scrollLeft = value;
            return this;
        }

        return results[this.getIndex()].scrollLeft;
    }

    this.onMouseDown = function (func: any) {
        results[this.getIndex()].onmousedown = func;
    }

    this.onMouseUp = function (func: any) {
        results[this.getIndex()].onmouseup = func;
    }

    this.offset = function () {
        return results[this.getIndex()].getBoundingClientRect();
    }

    this.remove = function () {
        results[this.getIndex()].parentNode.removeChild(results[this.getIndex()]);
    }

    this.lastChild = function () {
        return $(results[this.getIndex()].lastChild);
    }

    return this;
}