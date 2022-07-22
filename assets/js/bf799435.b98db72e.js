"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[44],{3905:function(t,e,r){r.d(e,{Zo:function(){return u},kt:function(){return d}});var n=r(7294);function a(t,e,r){return e in t?Object.defineProperty(t,e,{value:r,enumerable:!0,configurable:!0,writable:!0}):t[e]=r,t}function i(t,e){var r=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),r.push.apply(r,n)}return r}function o(t){for(var e=1;e<arguments.length;e++){var r=null!=arguments[e]?arguments[e]:{};e%2?i(Object(r),!0).forEach((function(e){a(t,e,r[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(r)):i(Object(r)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(r,e))}))}return t}function c(t,e){if(null==t)return{};var r,n,a=function(t,e){if(null==t)return{};var r,n,a={},i=Object.keys(t);for(n=0;n<i.length;n++)r=i[n],e.indexOf(r)>=0||(a[r]=t[r]);return a}(t,e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(t);for(n=0;n<i.length;n++)r=i[n],e.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(t,r)&&(a[r]=t[r])}return a}var l=n.createContext({}),s=function(t){var e=n.useContext(l),r=e;return t&&(r="function"==typeof t?t(e):o(o({},e),t)),r},u=function(t){var e=s(t.components);return n.createElement(l.Provider,{value:e},t.children)},p={inlineCode:"code",wrapper:function(t){var e=t.children;return n.createElement(n.Fragment,{},e)}},f=n.forwardRef((function(t,e){var r=t.components,a=t.mdxType,i=t.originalType,l=t.parentName,u=c(t,["components","mdxType","originalType","parentName"]),f=s(r),d=a,m=f["".concat(l,".").concat(d)]||f[d]||p[d]||i;return r?n.createElement(m,o(o({ref:e},u),{},{components:r})):n.createElement(m,o({ref:e},u))}));function d(t,e){var r=arguments,a=e&&e.mdxType;if("string"==typeof t||a){var i=r.length,o=new Array(i);o[0]=f;var c={};for(var l in e)hasOwnProperty.call(e,l)&&(c[l]=e[l]);c.originalType=t,c.mdxType="string"==typeof t?t:a,o[1]=c;for(var s=2;s<i;s++)o[s]=r[s];return n.createElement.apply(null,o)}return n.createElement.apply(null,r)}f.displayName="MDXCreateElement"},7140:function(t,e,r){r.r(e),r.d(e,{assets:function(){return u},contentTitle:function(){return l},default:function(){return d},frontMatter:function(){return c},metadata:function(){return s},toc:function(){return p}});var n=r(7462),a=r(3366),i=(r(7294),r(3905)),o=["components"],c={},l="Introduction",s={unversionedId:"Introduction",id:"Introduction",title:"Introduction",description:"Welcome to TLS-Anvil, our test suite for TLS 1.2 and 1.3 servers and clients. TLS-Anvil currently includes around 400 test cases that are based on requirements derived from various TLS related RFCs listed below as well as attacks from the past. The tests are implemented in Java using JUnit, coffee4j and TLS-Attacker and aim to detect violations of the TLS specification by TLS servers or clients.",source:"@site/docs/00-Introduction.md",sourceDirName:".",slug:"/Introduction",permalink:"/docs/Introduction",draft:!1,editUrl:"https://github.com/tls-attacker/TLS-Anvil/tree/main/Docs/docs/00-Introduction.md",tags:[],version:"current",sidebarPosition:0,frontMatter:{},sidebar:"tutorialSidebar",next:{title:"Quick Start",permalink:"/docs/Quick-Start/index"}},u={},p=[],f={toc:p};function d(t){var e=t.components,r=(0,a.Z)(t,o);return(0,i.kt)("wrapper",(0,n.Z)({},f,r,{components:e,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"introduction"},"Introduction"),(0,i.kt)("p",null,"Welcome to TLS-Anvil, our test suite for TLS 1.2 and 1.3 servers and clients. TLS-Anvil currently includes around 400 test cases that are based on requirements derived from various TLS related RFCs listed below as well as attacks from the past. The tests are implemented in Java using JUnit, coffee4j and TLS-Attacker and aim to detect violations of the TLS specification by TLS servers or clients."),(0,i.kt)("p",null,(0,i.kt)("strong",{parentName:"p"},"RFCs covered by tests:")),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc5246"},"RFC 5246")," - The Transport Layer Security (TLS) Protocol Version 1.2"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc8446"},"RFC 8446")," - The Transport Layer Security (TLS) Protocol Version 1.3"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc8701"},"RFC 8701")," - Applying Generate Random Extensions And Sustain Extensibility (GREASE) to TLS Extensibility"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7507"},"RFC 7507")," - TLS Fallback Signaling Cipher Suite Value (SCSV) for Preventing Protocol Downgrade Attacks"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc6066"},"RFC 6066")," - Transport Layer Security (TLS) Extensions: Extension Definitions"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7568"},"RFC 7568")," - Deprecating Secure Sockets Layer Version 3.0"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7919"},"RFC 7919")," - Negotiated Finite Field Diffie-Hellman Ephemeral Parameters for Transport Layer Security (TLS)"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7465"},"RFC 7465")," - Prohibiting RC4 Cipher Suites"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7366"},"RFC 7366")," - Encrypt-then-MAC for Transport Layer Security (TLS) and Datagram Transport Layer Security (DTLS)"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc8422"},"RFC 8422")," - Elliptic Curve Cryptography (ECC) Cipher Suites for Transport Layer Security (TLS) Versions 1.2 and Earlier"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7685"},"RFC 7685")," - A Transport Layer Security (TLS) ClientHello Padding Extension"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc6176"},"RFC 6176")," - Prohibiting Secure Sockets Layer (SSL) Version 2.0"),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("a",{parentName:"li",href:"https://datatracker.ietf.org/doc/html/rfc7457"},"RFC 7457")," - Summarizing Known Attacks on Transport Layer Security (TLS) and Datagram TLS (DTLS)")))}d.isMDXComponent=!0}}]);