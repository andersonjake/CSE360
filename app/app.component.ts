import { Component } from '@angular/core';
@Component({
    selector: 'my-app',
    templateUrl: './app/app.component.html'
})
export class AppComponent {
    output = '';

    changeListener($event) : void {
        this.readThis($event.target);
    }

    readThis(inputValue: any) : void {
        var file:File = inputValue.files[0];
        var myReader:FileReader = new FileReader();
        var output = '';
        this.output = output;
        console.log("output " + output);
        console.log(this.output);
        myReader.onloadend = function(e){
            // you can perform an action with readed data here
            console.log(myReader.result);
            output = myReader.result;
        }

        myReader.readAsText(file);
    }
}