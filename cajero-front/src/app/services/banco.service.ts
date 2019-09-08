import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BancoService {

  constructor(private http: HttpClient) {
  }

  getSaldoDisponible() {
    return this.http.get(`${environment.API}v1/cajero`, );
  }

  retirarEfectivo(cantidad: number) {
    console.log(`${environment.API}v1/cajero/retirar/${cantidad}`);
    return this.http.get(`${environment.API}v1/cajero/retirar/${cantidad}`, );
  }
}
