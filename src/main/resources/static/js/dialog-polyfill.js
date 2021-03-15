'use strict';
/**
 * Polyfill of dialog
 * for Edge, Firefox
 *
 * author Nemo
 *
 * v1.0
 * Supports:
 * show
 * hide
 * showModal
 * cancel(ESC)
 */

{
let UA = window.navigator.userAgent;
let browser = {
	
    isEdge: UA.includes('Edge'),
    isIE :UA.includes('IE'),
    isFirefox: UA.includes('Firefox'),
    isMSIE: UA.includes('MSIE'),
  
};

let pass = (func = '') => {
    console.log(`${func} passed.`);
}

let dlg = document.createElement('dialog');
let mask = undefined;

let initMask = () => {
    if (mask != undefined)return;

    mask = document.createElement('div');
    mask.style.display = 'none';
    mask.style.position = 'absolute';
    mask.style.zIndex = 999998;
    mask.style.top = 0;
    mask.style.left = 0;
    mask.style.right = 0;
    mask.style.bottom = 0;
    mask.style.backgroundColor = 'rgba(0, 0, 0, 0.3)';
    document.body.appendChild(mask);
};

// show support
if (!dlg.__proto__.show) {
    dlg.__proto__.open = false;

    dlg.__proto__.show = function() {
        this.open = true;

        this.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
        this.style.position = 'absolute';
        this.style.zIndex = 999999;
        this.style.display = 'table';
        this.style.top = '50%';
        this.style.transform = 'translate(0, -50%)';

        if (browser.isEdge) {
            this.style.left = '50%';
            this.style.transform = 'translate(-50%, -50%)';
        }

        return this;
    };    
} else {
    pass('show');
}

// showModal support
if (!dlg.__proto__.showModal) {
    initMask();

    dlg.__proto__.showModal = function() {
        mask.style.display = 'block';
        this.show();

        return this;
    };    
} else {
    pass('showModal');
}

// hide support
if (!dlg.__proto__.close) {
    initMask();

    dlg.__proto__.close = function() {
        this.open = false;

        mask.style.display = 'none';
        this.style.display = 'none';

        return this;
    }
} else {
    pass('hide');
}

// cancel(ESC) support
if (typeof dlg.oncancel == 'undefined') {
    let sEventType = browser.isEdge ? 'keydown' : 'keypress';
    const dialogClass = 'dlg-dialog';
    document.addEventListener(sEventType, (e) => {
        if (e.keyCode != 27)
            return;

        let dlgs = document.querySelectorAll(`.${dialogClass}`);
        for (let i = 0; i < dlgs.length; i++) {
            let d = dlgs[i];
            if (d.style.display == 'none')
                continue;

            if (d.wrapper.cancelable) {
                d.close();
            }
        }
    });
} else {
    pass('cancel');
}

}
