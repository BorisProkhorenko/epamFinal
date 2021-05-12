function confirmFunction(text, command) {
    console.log(text);
    console.log(command);
    if (window.confirm(text)) {
        window.location.replace(command);
    }

}