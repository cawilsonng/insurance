import {Component, Input} from '@angular/core';
import {Quotation} from "../../models/quotation";

@Component({
  selector: 'app-quote-result',
  templateUrl: './quote-result.component.html',
  styleUrls: ['./quote-result.component.css']
})
export class QuoteResultComponent {
  @Input() quotation: Quotation = {
    success: false,
    premium: 0,
    quote_reference: '',
  };
}
