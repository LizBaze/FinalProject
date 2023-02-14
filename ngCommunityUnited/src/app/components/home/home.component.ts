

import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig, NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { NgFor, NgIf } from '@angular/common';

@Component({
	selector: 'app-home',
	standalone: true,
	imports: [NgbCarouselModule, NgIf, NgFor],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
	providers: [NgbCarouselConfig], // add NgbCarouselConfig to the component providers
})
export class HomeComponent implements OnInit {
	showNavigationArrows = false;
	showNavigationIndicators = true;
	// images = [1055, 194, 368].map((n) => `https://picsum.photos/id/${n}/900/500`);
  images = ['https://images.pexels.com/photos/6994992/pexels-photo-6994992.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2', 'https://images.pexels.com/photos/6646918/pexels-photo-6646918.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','https://images.pexels.com/photos/9037596/pexels-photo-9037596.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'];


  ngOnInit(){


  };

	constructor(config: NgbCarouselConfig, private auth: AuthService) {
		// customize default values of carousels used by this component tree
		config.showNavigationArrows = false;
		config.showNavigationIndicators = true;
	}
}
