class UploadTilesetTemplates {

    public getGrid () : Array<HTMLElement> {
        let test: HTMLElement = document.createElement("p");
        test.innerText = "Customize difference tiles in this tileset. You can skipe this step.";

        return [test];
    }

    public getUploadForm () : Array<HTMLElement> {
        let form: HTMLElement = document.createElement("form");
        let tilesetNameFormGroup: HTMLElement = document.createElement("div");
        let uploadFormGroup: HTMLElement = document.createElement("div");

        tilesetNameFormGroup.className = "form-group";
        uploadFormGroup.className = "form-group";

        let tilesetLabel: HTMLElement = document.createElement("label");
        let tilesetInput: HTMLElement = document.createElement("input");
        let tilesetHelpText: HTMLElement = document.createElement("small");

        tilesetLabel.setAttribute("for", "tileset_name");
        tilesetLabel.innerText = "Nazwa tilesetu";
        tilesetInput.setAttribute("type", "text");
        tilesetInput.setAttribute("placeholder", "Enter tileset name");
        tilesetInput.setAttribute("name", "tileset_name");
        tilesetInput.classList.add("form-control");
        tilesetInput.id = "tileset_name";
        tilesetHelpText.innerText = "This will be used only in terrain editor to help group your tilesets.";

        tilesetNameFormGroup.appendChild(tilesetLabel);
        tilesetNameFormGroup.appendChild(tilesetInput);
        tilesetNameFormGroup.appendChild(tilesetHelpText);

        let uploadLabel: HTMLElement = document.createElement("label");
        let uploadInput: HTMLElement = document.createElement("input");
        let uploadHelpText: HTMLElement = document.createElement("small");

        uploadLabel.setAttribute("for", "tileset_file");
        uploadLabel.innerText = "Grafika tilesetu";
        uploadInput.setAttribute("type", "file");
        uploadInput.setAttribute("name", "tileset_file");
        uploadInput.setAttribute("accept", "image/bmp, image/png");
        uploadInput.id = "tileset_file";
        uploadInput.className = "form-control-file";
        uploadHelpText.innerText = "Wysokość i szerokość tilesetu powinna być podzielna przez 32!";

        uploadFormGroup.appendChild(uploadLabel);
        uploadFormGroup.appendChild(uploadInput);
        uploadFormGroup.appendChild(uploadHelpText);

        form.appendChild(tilesetNameFormGroup)
        form.appendChild(uploadFormGroup)

        return [form];
    }

}