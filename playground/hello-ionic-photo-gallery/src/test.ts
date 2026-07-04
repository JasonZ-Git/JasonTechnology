// This file is required by karma.conf.js and loads recursively all the .spec and framework files

import 'zone.js/testing';
import { getTestBed } from '@angular/core/testing';
import {
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting
} from '@angular/platform-browser-dynamic/testing';

// First, initialize the Angular testing environment.
getTestBed().initTestEnvironment(
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting()
);
import './app/app.component.spec';
import './app/explore-container/explore-container.component.spec';
import './app/services/photo.service.spec';
import './app/tab1/tab1.page.spec';
import './app/tab2/tab2.page.spec';
import './app/tab3/tab3.page.spec';
import './app/tabs/tabs.page.spec';
