import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import 'rxjs/add/operator/map';

@Injectable()
export class UserServie {

    constructor(private http: Http) { }

    getCouchBase(url: string) {
        return this.http.get(url)
            .map(res => res.json());
    }


}