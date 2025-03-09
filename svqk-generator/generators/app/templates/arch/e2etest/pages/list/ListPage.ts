<%_ include('../../../../lib/frontend-common', { entityNmPascal, compIdFields }); -%>
import <%= entityNmPascal %>ListPageElement from '@pages/<%= entityNmKebab %>-list/<%= entityNmPascal %>ListPageElement';
import <%= entityNmPascal %>InputPage from '@pages/<%= entityNmKebab %>-input/<%= entityNmPascal %>InputPage';
import BasePageElement from '@arch/BasePageElement';
<%- importDecIdTypeE2etest %>

export default class <%= entityNmPascal %>ListPage {
  private <%= entityNmCamel %>ListPageEl: <%= entityNmPascal %>ListPageElement;

  constructor(page: BasePageElement) {
    this.<%= entityNmCamel %>ListPageEl = new <%= entityNmPascal %>ListPageElement(page);
  }

  async gotoNew<%= entityNmPascal %>Page() {
    await this.<%= entityNmCamel %>ListPageEl.clickNew<%= entityNmPascal %>Link();
    return new <%= entityNmPascal %>InputPage(this.<%= entityNmCamel %>ListPageEl);
  }

  async goto<%= entityNmPascal %>ById(id: <%= idType%>) {
    await this.<%= entityNmCamel %>ListPageEl.click<%= entityNmPascal %>NoLinkById(id);
    return new <%= entityNmPascal %>InputPage(this.<%= entityNmCamel %>ListPageEl);
  }
}